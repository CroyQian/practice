package ioc.overview.domain;

import ioc.overview.Annotation.Super;

/**
 * @author Croy Qian
 * @createDate 2021/4/6
 * @Description TODO
 */
@Super
public class SuperUser extends User {
    private SuperUser() {

    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    @Override
    public String toString() {
        return "SuperUser{" + "address='" + address + '\'' + "} " + super.toString();
    }
}
