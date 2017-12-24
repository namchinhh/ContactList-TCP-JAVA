/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhba;

import java.util.ArrayList;

/**
 *
 * @author Nguyen Thanh Nam
 */
public interface ServerService {
    public void add(Person p);
    public Integer getPhone(String name);
    public Person search(String name);
}
