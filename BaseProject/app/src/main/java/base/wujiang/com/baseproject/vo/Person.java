package base.wujiang.com.baseproject.vo;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 概要说明 : 人员信息 <br>
 */
public class Person implements Cloneable, Serializable
{
    /**  
     * serialVersionUID:(用一句话描述这个变量表示什么).  
     */
    private static final long serialVersionUID = 2015923612111615676L;

    /**
     * id
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 所属单位
     */
    private String unitId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 职务类别id
     */
    private Integer positionTypeId;

    /**
     * 职务类别名称
     */
    private String positionTypeName;

    /**
     * 部门类别id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * qq号码
     */
    private String qq;

    /**
     * 出生日期
     */
    private String birthDay;

    /**
     * 行政区域
     */
    private String adArea;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 头像地址
     */
    private String headUrl;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lng;

    /**
     * 在位情况  1在位 2出动中3不在位
     */
    private String position;

    /**
     * 籍贯
     */
    private String jiguan;

    /**
     * 政治面貌
     */
    private String zzmm;

    /**
     * 人员详情
     */
    private String detailWord;

    /**
     * 所属车辆 
     */
    private String vehicleinfoId;

    /**
     * 车辆名称
     */
    private String vehicleInfoName;

    /**
     * 专业领域
     */
    private String spids;

    /**  
     * honid:安全员id  
     */
    private String honid;

    /**  
     * pimage:员工头像  
     */
    private String pimage;

    /**
     * 显示数据拼音的首字母
     */
    private String sortLetters;

    /**
     * 姓名缩写
     */
    private String shortName;

    /**
     * 通讯录聊天主体ID使用
     */
    private String masterId;

    /**
     * 单位名称
     */
    private String unitName;

    /**  
     * securityadmin:1:普通安全员  2：  
     */
    private String securityadmin;
    /**
     * 是否删除1未删除2删除
     */
    private String isDel;
    /**  
     * pname:职务名称
     */
    private String pname;
    private Bitmap myheadurl;
    
    public String getSecurityadmin()
    {
        return securityadmin;
    }

    public void setSecurityadmin(String securityadmin)
    {
        this.securityadmin = securityadmin;
    }

    public String getHeadUrl()
    {
        return headUrl;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getPimage()
    {
        return pimage;
    }

    public void setPimage(String pimage)
    {
        this.pimage = pimage;
    }

    public String getHonid()
    {
        return honid;
    }

    public void setHonid(String honid)
    {
        this.honid = honid;
    }

    public Bitmap getMyheadurl()
    {
        return myheadurl;
    }

    public void setMyheadurl(Bitmap myheadurl)
    {
        this.myheadurl = myheadurl;
    }
    /**  
     * keyname:公司名称  
     */
    private String keyname;

    public String getKeyname()
    {
        return keyname;
    }

    public void setKeyname(String keyname)
    {
        this.keyname = keyname;
    }
    /**
     * 克隆对象
     * @return person.
     */
    public Person clone()
    {
        try
        {
            return (Person) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getIsDel()
    {
        return isDel;
    }

    public void setIsDel(String isDel)
    {
        this.isDel = isDel;
    }

    public String getUnitName()
    {
        return unitName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public String getSpids()
    {
        return spids;
    }

    public void setSpids(String spids)
    {
        this.spids = spids;
    }

    public String getVehicleInfoName()
    {
        return vehicleInfoName;
    }

    public void setVehicleInfoName(String vehicleInfoName)
    {
        this.vehicleInfoName = vehicleInfoName;
    }

    public String getVehicleinfoId()
    {
        return vehicleinfoId;
    }

    public void setVehicleinfoId(String vehicleinfoId)
    {
        this.vehicleinfoId = vehicleinfoId;
    }

    public String getPositionTypeName()
    {
        return positionTypeName;
    }

    public void setPositionTypeName(String positionTypeName)
    {
        this.positionTypeName = positionTypeName;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public String getDetailWord()
    {
        return detailWord;
    }

    public void setDetailWord(String detailWord)
    {
        this.detailWord = detailWord;
    }

    public String getZzmm()
    {
        return zzmm;
    }

    public void setZzmm(String zzmm)
    {
        this.zzmm = zzmm;
    }

    public String getJiguan()
    {
        return jiguan;
    }

    public void setJiguan(String jiguan)
    {
        this.jiguan = jiguan;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUnitId()
    {
        return unitId;
    }

    public void setUnitId(String unitId)
    {
        this.unitId = unitId;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Integer getPositionTypeId()
    {
        return positionTypeId;
    }

    public void setPositionTypeId(Integer positionTypeId)
    {
        this.positionTypeId = positionTypeId;
    }

    public Integer getDepartmentId()
    {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId)
    {
        this.departmentId = departmentId;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public String getBirthDay()
    {
        return birthDay;
    }

    public void setBirthDay(String birthDay)
    {
        this.birthDay = birthDay;
    }

    public String getAdArea()
    {
        return adArea;
    }

    public void setAdArea(String adArea)
    {
        this.adArea = adArea;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

//    public String getHeadUrl()
//    {
//        return headUrl;
//    }

    public void setHeadUrl(String headUrl)
    {
        this.headUrl = headUrl;
    }

    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLng()
    {
        return lng;
    }

    public void setLng(String lng)
    {
        this.lng = lng;
    }

    public String getstateStr()
    {
        if (getPosition() != null)
        {
            if (getPosition().equals("1"))
            {
                return "在位";
            }
            else if (getPosition().equals("2"))
            {
                return "出动中";
            }
            else if (getPosition().equals("3"))
            {
                return "不在位";
            }
        }
        return "";
    }

    /**  
     * @return  the sortLetters  
     */
    public String getSortLetters()
    {
        return sortLetters;
    }

    /**  
     * @param   sortLetters    the sortLetters to set  
     */
    public void setSortLetters(String sortLetters)
    {
        this.sortLetters = sortLetters;
    }

    /**  
     * @return  the shortName  
     */
    public String getShortName()
    {
        return shortName;
    }

    /**  
     * @param   shortName    the shortName to set  
     */
    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    /**  
     * @return  the masterId  
     */
    public String getMasterId()
    {
        return masterId;
    }

    /**  
     * @param   masterId    the masterId to set  
     */
    public void setMasterId(String masterId)
    {
        this.masterId = masterId;
    }

}
