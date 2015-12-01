package org.knowm.datasets.common.business;

import java.util.ArrayList;
import java.util.List;

/**
 * @author timmolter
 */
public class BeanGenerator {

  public static void main(String[] args) {

    List<BeanProperty> beanProperties = new ArrayList<BeanProperty>();
    beanProperties.add(new BeanProperty("name", String.class));
    beanProperties.add(new BeanProperty("age", Integer.TYPE));
    beanProperties.add(new BeanProperty("height", Double.TYPE));

    String result = generateBeanFile("Person", "", beanProperties);
    System.out.println(result);
  }

  static class BeanProperty {

    private final String name;
    private final Class<?> type;

    BeanProperty(String name, Class<?> type) {

      this.name = name;
      this.type = type;
    }

    String getName() {

      return name;
    }

    Class<?> getType() {

      return type;
    }
  }

  static String generateBeanFile(String beanName, String header, List<BeanProperty> beanProperties) {

    StringBuilder sb = new StringBuilder();

    sb.append(header);

    sb.append("public class " + beanName + "\n");
    sb.append("{" + "\n");
    for (BeanProperty beanProperty : beanProperties) {
      sb.append("    private ");
      sb.append(beanProperty.getType().getSimpleName());
      sb.append(" ");
      sb.append(decapitalize(beanProperty.getName()));
      sb.append(";" + "\n");
    }
    sb.append("\n");
    sb.append("    public " + beanName + "()" + "\n");
    sb.append("    {" + "\n");
    sb.append("        // Default constructor" + "\n");
    sb.append("    }" + "\n");
    for (BeanProperty beanProperty : beanProperties) {
      sb.append("\n");
      sb.append(createSetterString(beanProperty));
      sb.append("\n");
      sb.append(createGetterString(beanProperty));
    }
    sb.append("}" + "\n");
    return sb.toString();
  }

  private static String createSetterString(BeanProperty beanProperty) {

    StringBuilder sb = new StringBuilder();

    sb.append("    public void set");
    sb.append(capitalize(beanProperty.getName()));
    sb.append("(");
    sb.append(beanProperty.getType().getSimpleName());
    sb.append(" ");
    sb.append(decapitalize(beanProperty.getName()));
    sb.append(")" + "\n");

    sb.append("    {" + "\n");

    sb.append("        this.");
    sb.append(decapitalize(beanProperty.getName()));
    sb.append(" = ");
    sb.append(decapitalize(beanProperty.getName()));
    sb.append(";" + "\n");

    sb.append("    }" + "\n");

    return sb.toString();
  }

  private static String createGetterString(BeanProperty beanProperty) {

    StringBuilder sb = new StringBuilder();

    sb.append("    public ");
    sb.append(beanProperty.getType().getSimpleName());
    sb.append(" get");
    sb.append(capitalize(beanProperty.getName()));
    sb.append("()" + "\n");

    sb.append("    {" + "\n");

    sb.append("        return ");
    sb.append(decapitalize(beanProperty.getName()));
    sb.append(";" + "\n");

    sb.append("    }" + "\n");

    return sb.toString();
  }

  private static String decapitalize(String s) {

    char c = Character.toLowerCase(s.charAt(0));
    return c + s.substring(1);
  }

  private static String capitalize(String s) {

    char c = Character.toUpperCase(s.charAt(0));
    return c + s.substring(1);
  }

}
