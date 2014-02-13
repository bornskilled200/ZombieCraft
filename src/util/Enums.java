package util;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/11/14
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Enums
{
    public static <T extends Enum<T>> T next(T t)
    {
        int index = t.ordinal();
        @SuppressWarnings("unchecked")
        Enum<T>[] constants = t.getDeclaringClass().getEnumConstants();
        if (index == constants.length - 1)
        {
            return (T)constants[0];
        }
        else
        {
            return (T)constants[index + 1];
        }
    }
}
