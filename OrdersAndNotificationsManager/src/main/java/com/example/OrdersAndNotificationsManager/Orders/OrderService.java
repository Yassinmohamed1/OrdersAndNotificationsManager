package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.NotificationObserver;
import com.example.OrdersAndNotificationsManager.NotificationSubject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements NotificationSubject {

    private List<NotificationObserver> observers = new ArrayList<>();

    public String placeOrder(Order order, List<String> productNames) {
        // Pass the list of product names to the order
        String result = order.placeorder(productNames);

        // Notify observers upon successful order placement
        if ("simple order placed".equals(result) || "compound order placed".equals(result)) {
            notifyObservers(result);
        }

        return result;
    }

    @Override
    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (NotificationObserver observer : observers) {
            observer.update(message);
        }
    }
}
