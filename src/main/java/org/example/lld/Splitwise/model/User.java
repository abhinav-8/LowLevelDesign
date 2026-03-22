package org.example.lld.Splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.Splitwise.observer.Observer;

@AllArgsConstructor
@Setter
@Getter
public class User implements Observer {
    private String id;
    private String name;
    private String email;
    private String phone;
    //Other metadata

    @Override
    public void update(String message) {
        System.out.println("Notify " + id + ": " + message);
    }
}
