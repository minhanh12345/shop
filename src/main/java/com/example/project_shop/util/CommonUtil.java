package com.example.project_shop.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class CommonUtil {

    public static Date convertStringToTimeStamp(String transferStatusDate, String formatDate) {
        if (transferStatusDate == null) {
            return null;
        }

        try {
            Date date1 = new SimpleDateFormat(formatDate).parse(transferStatusDate);
            return convertToSqlDate(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date convertTimestampToDate(Timestamp timestamp) {
        if(timestamp == null) {
            return  null;
        }

        java.sql.Date date = new java.sql.Date(timestamp.getTime());
        return date;
    }

    public static String convertDateSqlToString(java.sql.Date date, String formatDate) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        return sdf.format(date);
    }

    public static int calculateAge(LocalDate birthDate) {
        if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    public static Timestamp convertToSqlDate(Date transferStatusDate) {
        if (transferStatusDate == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(transferStatusDate);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }
    public static Pageable buildPageable(int pageIndex, int pageSize, String sortColumn, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toLowerCase());

        return  PageRequest.of(pageIndex, pageSize, Sort.by(direction, sortColumn));
    }
    public static String convertDateToStringFormatDate(Timestamp dateTime, String formatDate) {
        if (dateTime == null) {
            return "";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
        return dateFormat.format(dateTime);
    }

    public static boolean stringIsNullOrEmpty(String param) {
        return param == null || param.isEmpty();
    }


    public static String convertListToString(List<String> list, CharSequence delimiter) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        if (list.size() == 1) {
            return list.get(0);
        }

        return String.join(delimiter, list);
    }

    public static String getFullName(String... names) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(names[0])) {
            stringBuilder.append(names[0]);
        }

        if (StringUtils.isNotBlank(names[1])) {
            stringBuilder.append(" ").append(names[1]);
        }

        if (StringUtils.isNotBlank(names[2])) {
            stringBuilder.append(" ").append(names[2]);
        }

        if (StringUtils.isNotBlank(names[3])) {
            stringBuilder.append(" ").append(names[3]);
        }

        return stringBuilder.toString();
    }

    public static List<String> convertStringToList(String s, CharSequence delimiter) {
        if (s == null) {
            return new ArrayList<>();
        }

        if (!s.contains(delimiter)) {
            return new ArrayList<>(Collections.singleton(s));
        }

        return Arrays.stream(s.split(String.valueOf(delimiter))).collect(Collectors.toList());
    }


    public static List<List<String>> convertRawListByFieldNumber(List<String> list, int numberField) {
        List<String> sectionFieldList = new ArrayList<>(list);
        List<List<String>> allList = new ArrayList<>();
        if (sectionFieldList.isEmpty())
            return allList;

        //add virtual value to origin list: exp: numberField = 4, sectionFieldList size = 9 -> add 3 empty string value to list
        // if size = 7-> add 1, size = 8 -> no add
        int numberVirtualElement = numberField - sectionFieldList.size() % numberField;
        if (numberVirtualElement != numberField) {
            for (int i = 1; i <= numberVirtualElement; i++) {
                sectionFieldList.add(StringUtils.EMPTY);
            }
        }

        List<String> oneTempList = new ArrayList<>();
        for (String field : sectionFieldList) {
            if (oneTempList.size() < numberField) {
                oneTempList.add(field);
                if (oneTempList.size() == numberField) {
                    allList.add(oneTempList);
                    oneTempList = new ArrayList<>();
                }
            }
            ;
        }
        return allList;
    }


    public static List<String> filterEmptyStringList(List<String> listStr) {
        if (listStr != null && !listStr.isEmpty()) {
            return listStr.stream().filter(StringUtils::isNotBlank).map(StringUtils::trim).collect(Collectors.toList());
        } else
            return new ArrayList<>();
    }

    public static Double calculateSeniority(Timestamp hireDate) {
        if (hireDate == null) return 0D;
        LocalDateTime hireDateTime = hireDate.toLocalDateTime();
        long days = Duration.between(hireDateTime, LocalDateTime.now()).toDays();
        if (days < 0) days = 0;
        return (double) days / 365;
    }

    public static String calculateAgeRange(Integer ageInteger) {
        if (ageInteger == null) ageInteger = 0;
        String ageRange = StringUtils.EMPTY;
        if (18 <= ageInteger && ageInteger < 26) {
            ageRange = "18 to 25 years";
        }

        if (26 <= ageInteger && ageInteger < 36) {
            ageRange = "26 to 35 years";
        }

        if (36 <= ageInteger && ageInteger < 41) {
            ageRange = "36 to 40 years";
        }

        if (41 <= ageInteger && ageInteger < 46) {
            ageRange = "41 to 45 years";
        }

        if (46 <= ageInteger && ageInteger < 55) {
            ageRange = "46 to 54 years";
        }

        if (55 <= ageInteger && ageInteger < 60) {
            ageRange = "55 to 59 years";
        }

        if (60 < ageInteger) {
            ageRange = "60 years old or more";
        }

        return ageRange;
    }

    public static String calculateSeniorityRange(Double subSenior) {
        if (subSenior == null) {
            return StringUtils.EMPTY;
        }
        String seniorityRange = StringUtils.EMPTY;
        if (subSenior < 1) {
            seniorityRange = "Less than 1 year";
        }

        if (1 <= subSenior && subSenior < 4) {
            seniorityRange = "1-3 years";
        }

        if (4 <= subSenior && subSenior < 6) {
            seniorityRange = "4-5 years";
        }

        if (6 <= subSenior && subSenior < 11) {
            seniorityRange = "6-10 years";
        }

        if (11 <= subSenior && subSenior < 16) {
            seniorityRange = "11-15 years";
        }

        if (16 <= subSenior && subSenior < 20) {
            seniorityRange = "16-19 years";
        }

        if (20 <= subSenior) {
            seniorityRange = "20 years and more";
        }

        return seniorityRange;
    }


    ;

}
