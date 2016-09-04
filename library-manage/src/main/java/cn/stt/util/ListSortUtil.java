package cn.stt.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * ����һ���Զ���������࣬ר������б�List���е����ݽ������򣻿ɰ�ָ���������С�
 * Ŀǰʵ�ֶ��ַ�����String�������ڣ�Date�������ͣ�Integer�������ֶ����������
 * Created by stt on 2016/9/3.
 */
public class ListSortUtil<E> {
    /**
     * ���б��е����ݰ�ָ���ֶν�������Ҫ�����������صķ��������ַ��������͡����ڵ�ֵ�Խ��бȽϡ�
     * @param list
     * @param method
     * @param reverseFlag
     */
    public void sortByMethod(List<E> list, final String method,
                             final boolean reverseFlag) {
        Collections.sort(list, new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object arg1, Object arg2) {
                int result = 0;
                try {
                    Method m1 = ((E) arg1).getClass().getMethod(method, null);
                    Method m2 = ((E) arg2).getClass().getMethod(method, null);
                    Object obj1 = m1.invoke(((E) arg1), null);
                    Object obj2 = m2.invoke(((E) arg2), null);
                    if (obj1 instanceof String) {
                        // �ַ���
                        result = obj1.toString().compareTo(obj2.toString());
                    } else if (obj1 instanceof Date) {
                        // ����
                        long l = ((Date) obj1).getTime() - ((Date) obj2).getTime();
                        if (l > 0) {
                            result = 1;
                        } else if (l < 0) {
                            result = -1;
                        } else {
                            result = 0;
                        }
                    } else if (obj1 instanceof Integer) {
                        // ���ͣ�Method�ķ��ز���������int�ģ���ΪJDK1.5֮��Integer��int�����Զ�ת���ˣ�
                        result = (Integer) obj1 - (Integer) obj2;
                    } else {
                        // Ŀǰ�в�֧�ֵĶ���ֱ��ת��ΪString��Ȼ��Ƚϣ����δ֪
                        result = obj1.toString().compareTo(obj2.toString());

                        System.err.println("ListSortUtil.sortByMethod�������ܵ�����ʶ��Ķ������ͣ�ת��Ϊ�ַ�����ȽϷ���...");
                    }

                    if (reverseFlag) {
                        // ����
                        result = -result;
                    }
                } catch (NoSuchMethodException nsme) {
                    nsme.printStackTrace();
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                } catch (InvocationTargetException ite) {
                    ite.printStackTrace();
                }

                return result;
            }
        });
    }

    public void sortByField(List<E> list,final String field, final boolean reverseFlag){
        Collections.sort(list, new Comparator<E>() {
            public int compare(E o1, E o2) {
                int result = 0;
                String methodName = "get"+MyStringUtil.toUpperCaseFirstOne(field);
                try {
                    Method m1 = o1.getClass().getMethod(methodName, null);
                    Method m2 = o2.getClass().getMethod(methodName, null);
                    Object invoke1 = m1.invoke(o1, null);
                    Object invoke2 = m2.invoke(o2, null);
                    /*Method m1 = o1.getClass().getMethod(methodName, null);
                    Object invoke1 = m1.invoke(E, null);*/
                    if (invoke1 instanceof String){
                        result = invoke1.toString().compareTo(invoke2.toString());
                    }else if(invoke1 instanceof Date){
//                        result = (int) (((Date) invoke1).getTime() - ((Date) invoke2).getTime());
                        long l = ((Date) invoke1).getTime() - ((Date) invoke2).getTime();
                        if (l > 0){
                            result = 1;
                        }else if (l < 0){
                            result = -1;
                        }else {
                            result = 0;
                        }
                    }else if (invoke1 instanceof Integer){
                        result = (Integer)invoke1 - (Integer)invoke2;
                    }else{
                        // Ŀǰ�в�֧�ֵĶ���ֱ��ת��ΪString��Ȼ��Ƚϣ����δ֪
                        result = invoke1.toString().compareTo(invoke2.toString());
                        System.err.println("ListSortUtil.sortByMethod�������ܵ�����ʶ��Ķ������ͣ�ת��Ϊ�ַ�����ȽϷ���...");
                    }
                    if(reverseFlag){//��������
                        result = -result;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return result;
            }
        });
    }
}
