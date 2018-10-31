package gson;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by zhuhaoju on 2018/2/8.
 */
@Data
@NoArgsConstructor
public class User {

    //省略其它
    @SerializedName(value = "small_name",alternate = {"s_na","na"})
    public String name;
    public int age;
    public String email;
    public Date date;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
