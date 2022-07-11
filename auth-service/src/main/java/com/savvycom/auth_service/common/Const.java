package com.savvycom.auth_service.common;

/**
 * Định nghĩa các status
 */
public class Const {

    public static class API_RESPONSE {
        public static final String API_STATUS_OK_STR = "200";
        public static final String API_STATUS_BAD_REQUEST_STR = "400";
        public static final String API_STATUS_INTERNAL_SERVER_ERROR_STR = "500";
        public static final Boolean RESPONSE_STATUS_TRUE = true;
        public static final Boolean RESPONSE_STATUS_FALSE = false;
        public static final String RETURN_DES_FAILURE_NOTFOUND = "Not Found";
        public static final String DESCRIPTION_DEFAULT = "error";
        public static final String INTERNAL_SERVER_ERROR = "internal server error";

        private API_RESPONSE() {
            throw new IllegalStateException();
        }
    }


 
}

