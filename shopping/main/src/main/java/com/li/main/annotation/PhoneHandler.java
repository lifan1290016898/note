package com.li.main.annotation;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneHandler implements ConstraintValidator<Phone, Object> {

    String type;

    @Override
    public void initialize(Phone constraintAnnotation) {
        type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.equals(type, "Phone")){
            // 验证手机号
            Pattern phoneReg = Pattern.compile("^(1[3456789])\\d{9}$");
            Matcher m = phoneReg.matcher(String.valueOf(o));
            return m.matches();
        } else if(StringUtils.equals(type, "Fax")){
            // 传真 格式：0111-1111111
            Pattern faxReg = Pattern.compile("^((0\\d{2,3}-)?\\d{7,8})$");
            Matcher m = faxReg.matcher(String.valueOf(o));
            return m.matches();
        } else if(StringUtils.equals(type, "Landline")){
            // 座机号
            String str = String.valueOf(o);
            Pattern p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
            Pattern  p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");     // 验证没有区号的
            Matcher m = null;
            boolean isPhone = false;
            if (str.length() > 9) {
                m = p1.matcher(str);
                isPhone = m.matches();
            } else {
                m = p2.matcher(str);
                isPhone = m.matches();
            }
            return isPhone;
        }
        return false;
    }
}
