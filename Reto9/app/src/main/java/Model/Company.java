package Model;

/**
 * Created by vr on 11/11/17.
 */

public class Company {
    private long companyId;
    private String name;
    private String url;
    private String phone;
    private String email;
    private String products;
    private String type; //Clasificación de la empresa: consultoría, desarrollo a la medida y/o fábrica de software.

    public Company(){}

    public Company( long companyId, String name, String url, String phone, String email, String products, String type ){
        this.companyId = companyId;
        this.name = name;
        this.url = url;
        this.phone = phone;
        this.email = email;
        this.products = products;
        this.type = type;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Id: " + companyId + '\n' +
                "Name: " + name + '\n' +
                "Url: " + url + '\n' +
                "Phone: " + phone + '\n' +
                "Email: " + email + '\n' +
                "Products and Services: " + products + '\n' +
                "Type: " + type;
    }
}