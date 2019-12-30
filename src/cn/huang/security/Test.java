package security;

public class Test {
    
    public static void main(String[] args) {
      String userId = "b447fa412e9f467a9472e5978cdc5e11";
      String companyId = "05d8e9a3167e4c27ac08152cc0103b75";
      String content = String.valueOf((userId + companyId).hashCode());
      String key = String.valueOf(System.currentTimeMillis());
      String result = QEncodeUtil.aesEncrypt(content, key);
      System.out.println(result);
      System.out.println("d1CrXAuYcnZtbC8Q8oWKZQ==".equals(result));
    }
}
