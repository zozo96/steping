package excel.poi;

/**
 * @author : Sonya
 * @date : 2020/7/22 17:28
 */
public class ExtendedFieldDTO {
    private String name;
    private Integer required;

    public ExtendedFieldDTO(String name, Integer required){
        this.name = name;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }
}
