/**
 * 金鸽公司源代码，版权归金鸽公司所有。<br>
 * 项目名称 : JingeApp
 */

package base.wujiang.com.baseproject.base;

import java.io.Serializable;

/**  
 * 概要说明 : 数据返回信息.  <br>
 */
public class ReObject implements Serializable
{
    /**  
     * serialVersionUID:(用一句话描述这个变量表示什么).  
     */
    private static final long serialVersionUID = 6046910639131004782L;

    /**
     * 
     */
    private Boolean success;

    /**
     * 
     */
    private Object content;

    /**
     * 
     */
    private Object extra;

    /**
     * 
     */
    private int pageCount;

    public Boolean getSuccess()
    {
        return success;
    }

    public void setSuccess(Boolean success)
    {
        this.success = success;
    }

    public Object getContent()
    {
        return content;
    }

    public void setContent(Object content)
    {
        this.content = content;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public Object getExtra()
    {
        return extra;
    }

    public void setExtra(Object extra)
    {
        this.extra = extra;
    }
}
