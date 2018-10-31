package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by zhuhaoju on 2018/2/8.
 */
public class GsonTest {

    @Test
    public void test(){

        User user = new User("zhangsan",12,"123@qq.com");
        String s = new Gson().toJson(user);

        User u = new Gson().fromJson(s, User.class);
        System.out.println(u.toString());
        System.out.println(s);

    }

    @Test
    public void test2(){

        Gson gson = new Gson();
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String[] strings = gson.fromJson(jsonArray, String[].class);
        for (String str : strings){
            System.out.println(str);
        }
    }

    @Test
    public void test3(){

        Gson gson = new Gson();
        Map map = new HashMap();

        map.put("1","1");
        map.put("2","2");
        map.put("3","3");

        Map map1 = gson.fromJson(gson.toJson(map), Map.class);
        System.out.println(map1);

    }

    @Test
    public void test4(){

        Gson gson = new Gson();
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        List<String> strings = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
        System.out.println(strings);

        List list1 = gson.fromJson(jsonArray, List.class);
        System.out.println(list1);


        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan",12,"123@qq.com"));
        list.add(new User("zhangsan",12,"123@qq.com"));
        list.add(new User("zhangsan",12,"123@qq.com"));

        String s = gson.toJson(list);
        List<User> users = gson.fromJson(s,List.class);
        System.out.println(users);

    }

    @Test
    public void test5() throws IOException {
        String json = "{\"name\":\"怪盗kidou\",\"age\":\"24\"}";
        User user = new User();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.beginObject(); // throws IOException
        while (reader.hasNext()) {
            String s = reader.nextName();
            switch (s) {
                case "name":
                    user.name = reader.nextString();
                    break;
                case "age":
                    user.age = reader.nextInt(); //自动转换
                    break;
                case "email":
                    user.email = reader.nextString();
                    break;
            }
        }
        reader.endObject(); // throws IOException
        System.out.println(user.name);  // 怪盗kidou
        System.out.println(user.age);   // 24
        System.out.println(user.email); // ikidou@example.com

    }

    @Test
    public void test6(){
        Gson gson = new Gson();
        User user = new User("怪盗kidou",24,"ikidou@example.com");
        gson.toJson(user,System.out); // 写到控制台

        StringBuffer stringBuffer = new StringBuffer();
        gson.toJson(user,stringBuffer);
        System.out.println(stringBuffer.toString());
    }

    @Test
    public void test7(){
//        Gson gson = new GsonBuilder()
//                        .serializeNulls()
//                        .create();
//        User user = new User();
//        System.out.println(gson.toJson(user));

        Gson gson = new GsonBuilder()
                //序列化null
                .serializeNulls()
                // 设置日期时间格式，另有2个重载方法
                // 在序列化和反序化时均生效
                .setDateFormat("yyyy-MM-dd")
                // 禁此序列化内部类
//                .disableInnerClassSerialization()
//                //生成不可执行的Json（多了 )]}' 这4个字符）
//                .generateNonExecutableJson()
//                //禁止转义html标签
//                .disableHtmlEscaping()
//                //格式化输出
//                .setPrettyPrinting()
                .create();
                User user = new User();
                user.setDate(new Date());

        System.out.println(gson.toJson(user));

    }


}
