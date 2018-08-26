package com.runxsports.provider.cs.cms.common.util;

import java.util.Collection;

import org.apache.commons.lang3.Validate;

import com.runxsports.provider.cs.cms.common.constant.enumerate.GlobalCallbackEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsErrorCodeEnum;
import com.runxsports.provider.cs.cms.common.exception.CmsException;

/**
 * @author Created by Jeff.hou
 * @date on 2018/3/27.
 */
public class ValidateUtils {

    public static <T extends CharSequence> void notBlank(final T chars, final GlobalCallbackEnum globalCallbackEnum) {
        try {
            Validate.notBlank(chars);
        } catch (Exception e) {
            throw new CmsException(globalCallbackEnum);
        }
    }
    
    public static <T extends CharSequence> void notBlank(final T chars, final CmsErrorCodeEnum globalCallbackEnum) {
        try {
            Validate.notBlank(chars);
        } catch (Exception e) {
            throw new CmsException(globalCallbackEnum);
        }
    }

    public static <T> void notNull(final T object, final GlobalCallbackEnum globalCallbackEnum) {
        try {
            Validate.notNull(object);
        } catch (Exception e) {
        	throw new CmsException(globalCallbackEnum);
        }
    }

    public static void isTrue(final boolean expression, final GlobalCallbackEnum globalCallbackEnum) {
        try {
            Validate.isTrue(expression);
        } catch (Exception e) {
        	throw new CmsException(globalCallbackEnum);
        }
    }

    public static void matchesPattern(final CharSequence input, final String pattern, final GlobalCallbackEnum globalCallbackEnum) {
        try {
            Validate.matchesPattern(input, pattern);
        } catch (Exception e) {
        	throw new CmsException(globalCallbackEnum);
        }
    }

    public static <T extends Collection<?>> void notEmpty(final T collection, final GlobalCallbackEnum globalCallbackEnum) {
        try {
            Validate.notEmpty(collection);
        } catch (Exception e) {
        	throw new CmsException(globalCallbackEnum);
        }
    }
}