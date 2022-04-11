package com.example.project_shop.constant;

public class ErrorConstant {
    public class Code{
        public static final int LOGIN_INVALID = 01;
        public static final int USER_INACTIVE = 02;
    }

    public class Type{
        public static final String LOGIN_INVALID = "LOGIN_INVALID";
        public static final String USER_INACTIVE = "USER_INACTIVE";
    }

    public class MessageEN{
        public static final String LOGIN_INVALID = "Username or password invalid";
        public static final String USER_INACTIVE = "User inactive";
    }

    public class MessageVI{
        public static final String LOGIN_INVALID = "Thông tin đăng nhập không đúng";
        public static final String USER_INACTIVE = "Người dùng đang bị khoá";
    }
}
