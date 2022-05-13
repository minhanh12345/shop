package com.example.project_shop.util;

public class Constant {
    //    public static final String CATALOG = "Recognition_DEV"; // HRIS
//    public static final String SCHEMA = "dbo";
//    public static final int MAX_LENGTH_ENCODE = 20;
//    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
//    public static final int TIME_RESEND_EMAIL = 30;
//    public static final String EMAIL_TEMPLATE_FORGOT_PASSWORD = "EMAIL_TEMPLATE_FORGOT_PASSWORD";
//    public static final int EXPIRED_TIME_RESET_PASSWORD = 12;
//    public static final String SPLIT_LIST_STRING = ";";
    public class Role {
        public static final String CUSTOMER = "CUSTOMER";
        public static final String SHIPPER = "SHIPPER";
        public static final String ADMIN = "ADMIN";
        public static final String STAFF = "STAFF";
    }

    public class Code {
        public static final int SUCCESS = 200;
        public static final int NOT_FOUND = 400;
        public static final int LOGIN_FAIL = 401;
        public static final int CREATED_FAIL = 301;
        public static final int UPDATED_FAIL = 301;
        public static final int DELETED_FAIL = 301;
        public static final int BAD_REQUEST = 400;
        public static final int BAD_PARAM = 0;
        public static final int FORBIDDEN = 403;
    }

    public class ErrorType {
        public static final String SUCCESS = "SUCCESS";
        public static final String LOGIN_USER_NOT_FOUND = "LOGIN_USER_NOT_FOUND";
        public static final String LOGIN_FAIL = "LOGIN_FAIL";
        public static final String RefreshToken_NotFound = "NOT_FOUND_REFRESHTOKEN";
        public static final String RefreshToken_expired = "RefreshToken_expired";

    }


    public static class Message {
        public static final String SUCCESS = "success";
        public static final String LOGIN_FAIL = "Email or password is not correct !";
        public static final String USER_NOT_FOUND = "not found user";
        public static  final  String RefreshToken_NotFound = "Refresh token is empty!";
        public static final String RefreshToken_expired = "Refresh token was expired. Please make a new signin request";
    }
    public class CodeRes {
        public static final String SUCCESS = "200";
        public static final String NOT_FOUND = "400";
        public static final String BAD_REQUEST = "400";
        public static final String INTERNAL_SERVER_ERROR = "400";
    }

    public enum EmailType {
        CREATION("Creation"),
        EDITION("Edition"),
        BOTH("Both"),
        ASKING_APPROVAL("Asking Approval"),
        RESPONSE_APPROVAL("Response Approval"),
        ASSIGNATION("Assignation");

        private final String emailType;

        EmailType(String emailType) {
            this.emailType = emailType;
        }

        public String getEmailType() {
            return emailType;
        }
    }

    public enum EmailTemplate {
        JOB_DESCRIPTION("Job Description"),
        JOB_POSITIONS("Job Positions"),
        JOB_VALUATION("Job Valuation"),
        COMPETENCIES("Competencies"),
        BENEFITS("Benefits"),
        JOB_PROFILE("Job Profile");

        private final String emailTemplate;

        EmailTemplate(String emailTemplate) {
            this.emailTemplate = emailTemplate;
        }

        public String getEmailTemplate() {
            return emailTemplate;
        }
    }


    public enum NotificationReferenceType {
        CREATION_JOB_VALUATION,
        EDITION_JOB_VALUATION,
        APPROVAL_JOB_VALUATION,
        APPROVAL_JOB_PROFILE,
        CREATION_JOB_POSITION,
        CHECK_BENEFITS,
    }


}
