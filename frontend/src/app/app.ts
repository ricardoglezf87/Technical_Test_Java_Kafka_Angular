import { Component, OnInit, signal } from '@angular/core';
import { Order } from './models/order';
import { OrderService } from './services/order.service';
import { CommonModule } from '@angular/common';
import { OrderForm } from './components/order-form/order-form';
import { OrderList } from './components/order-list/order-list';

@Component({
  imports: [CommonModule, OrderForm, OrderList],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App implements OnInit {
  
  orders = signal<Order[]>([]);

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
   
    this.orderService.getOrders().subscribe({
      next:(data) => {
      console.log(data);
      this.orders.set(data);
    },
    error:(error) => {
      console.error('Error fetching orders:', error);
    }});
  }

  onOrderCreated(order: Order) {
    this.orders.update(orders => [...orders, order]);
  }
}
