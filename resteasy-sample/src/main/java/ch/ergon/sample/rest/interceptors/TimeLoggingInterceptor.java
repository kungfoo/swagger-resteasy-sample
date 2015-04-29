package ch.ergon.sample.rest.interceptors;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.google.common.base.Stopwatch;

@Interceptor
@LogExecutionTime
public class TimeLoggingInterceptor {

	private static final Logger LOGGER = Logger.getLogger(TimeLoggingInterceptor.class.getSimpleName());

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		Stopwatch stopwatch = Stopwatch.createStarted();
		try {
			return context.proceed();
		} finally {

			String className = context.getMethod().getDeclaringClass().getSimpleName();
			String methodName = context.getMethod().getName();
			long timeInMs = stopwatch.elapsed(TimeUnit.MILLISECONDS);

			String message = String.format("%s.%s() %d ms", className, methodName, timeInMs);
			LOGGER.info(message);
		}
	}
}
