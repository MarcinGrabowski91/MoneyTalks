package eu.gitcode.android.moneytalks.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import eu.gitcode.android.moneytalks.enumeration.HttpExceptionEnum;
import retrofit2.adapter.rxjava.HttpException;

public final class NetworkUtils {

    public static final String unknownError = "Unknown error";

    private NetworkUtils() {
        throw new AssertionError();
    }

    public static String getExceptionText(Throwable throwable) {
        if (throwable != null) {
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                return getExceptionText(httpException.code());
            } else if (throwable instanceof SocketTimeoutException) {
                return "Connection timeout error";
            } else if (throwable instanceof UnknownHostException) {
                return "Can not connect to the server";
            } else {
                return unknownError;
            }
        } else {
            return unknownError;
        }
    }

    private static String getExceptionText(int code) {
        switch (code) {
            case 400:
                return "HTTP Error 400: Bad Request";
            case 401:
                return "HTTP Error 401: Unauthorized";
            case 403:
                return "HTTP Error 403: Forbidden";
            case 404:
                return "HTTP Error 404: Not found";
            case 500:
                return "HTTP Error 500: Internal server error";
            case 503:
                return "HTTP Error 503: Service unavailable";
            case 504:
                return "HTTP Error 504: Gateway timeout";
            default:
                return unknownError;
        }
    }

    public static boolean isInternalServerException(Throwable throwable) {
        return getHttpException(throwable).equals(HttpExceptionEnum.INTERNAL_SERVER_ERROR);
    }

    public static boolean isUnauthorizedException(Throwable throwable) {
        return getHttpException(throwable).equals(HttpExceptionEnum.UNAUTHORIZED);
    }

    public static boolean isServiceUnavailableException(Throwable throwable) {
        return getHttpException(throwable).equals(HttpExceptionEnum.SERVICE_UNAVAILABLE);
    }

    public static boolean isBadRequestException(Throwable throwable) {
        return getHttpException(throwable).equals(HttpExceptionEnum.BAD_REQUEST);
    }

    public static boolean isUnauthorizedCode(int code) {
        return NetworkUtils.getHttpException(code) == HttpExceptionEnum.UNAUTHORIZED;
    }

    public static String getExceptionErrorBody(Throwable throwable) {
        if (throwable instanceof HttpException) {
            try {
                return ((HttpException) throwable).response().errorBody().string();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    private static HttpExceptionEnum getHttpException(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            return getHttpException(httpException.code());
        } else if (throwable instanceof SocketTimeoutException) {
            return HttpExceptionEnum.TIMEOUT;
        } else {
            return HttpExceptionEnum.UNDEFINED;
        }
    }

    private static HttpExceptionEnum getHttpException(int code) {
        switch (code) {
            case 400:
                return HttpExceptionEnum.BAD_REQUEST;
            case 401:
                return HttpExceptionEnum.UNAUTHORIZED;
            case 403:
                return HttpExceptionEnum.FORBIDDEN;
            case 404:
                return HttpExceptionEnum.NOT_FOUND;
            case 500:
                return HttpExceptionEnum.INTERNAL_SERVER_ERROR;
            case 503:
                return HttpExceptionEnum.SERVICE_UNAVAILABLE;
            case 504:
                return HttpExceptionEnum.GATEWAY_TIMEOUT;
            default:
                return HttpExceptionEnum.UNDEFINED;
        }
    }
}
