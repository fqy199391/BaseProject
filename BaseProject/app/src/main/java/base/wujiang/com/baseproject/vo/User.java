package base.wujiang.com.baseproject.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

//@Entity（nameInDb = "user_info",indexes = {@Index(value = "name DESC", unique = true)},）
@Entity(nameInDb = "user_info",indexes = {@Index(value = "name DESC", unique = true)})
public class User {
    @Id
    private Long id;

    //命名数据库中字段的名称，不为空
    @Property(nameInDb = "USERNAME") @NotNull
    private String name;

    @Transient//表示不存在数据库中
    private int tempUsageCount; // not persisted

    @Generated(hash = 1709734220)
    public User(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTempUsageCount() {
        return tempUsageCount;
    }

    public void setTempUsageCount(int tempUsageCount) {
        this.tempUsageCount = tempUsageCount;
    }
}