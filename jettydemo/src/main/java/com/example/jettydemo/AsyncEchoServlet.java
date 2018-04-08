package com.example.jettydemo;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by yuhuijuan on 2018/2/22
 */
public class AsyncEchoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		AsyncContext asyncContext = req.startAsync(req, resp);
		asyncContext.setTimeout(0);
		Echoer echoer =new Echoer(asyncContext);
		req.getInputStream().setReadListener(echoer);
		resp.getOutputStream().setWriteListener(echoer);
	}

	private static class Echoer implements ReadListener, WriteListener {

		private final byte[] buffer = new byte[4096];

		private final AsyncContext asyncContext;

		private final ServletInputStream input;

		private final ServletOutputStream output;

		private final AtomicBoolean complete = new AtomicBoolean(false);

		private Echoer(AsyncContext asyncContext) throws IOException {
			this.asyncContext = asyncContext;
			this.input = asyncContext.getRequest().getInputStream();
			this.output = asyncContext.getResponse().getOutputStream();
		}

		@Override
		public void onDataAvailable() throws IOException {
			handleAsyncIO();
		}

		@Override
		public void onAllDataRead() throws IOException {
			handleAsyncIO();
		}

		@Override
		public void onWritePossible() throws IOException {
			handleAsyncIO();
		}

		@Override
		public void onError(Throwable throwable) {
			new Throwable("onError", throwable).printStackTrace();
			asyncContext.complete();
		}

		private void handleAsyncIO() throws IOException {
			//在以下情况下将会被调用
			//1.在初次注册WriteListener (准备第一次写入)
			//2.在初次注册ReadListener
			//3.当前一次写完成了，但output.isReady()返回false，
			//4.input 的callback调用

			while (true) {
				if (!output.isReady()) {
					//当onWritePossible ()被唤醒时，如果不可写，也不要去尝试读取
					break;
				}
				if (!input.isReady()) {
					break;
				}

				int read = input.read(buffer);

				if (read < 0) {
					if (complete.compareAndSet(false, true)) {
						asyncContext.complete();
					}
					break;
				} else if (read > 0) {
					output.write(buffer, 0, read);
				}

			}
		}

	}

}
