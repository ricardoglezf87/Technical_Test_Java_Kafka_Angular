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
    this.loadOrders();    
  }

  onOrderCreated(order: Order) {
    this.loadOrders();
  }

  onRetry(orderId: string) {
    this.orderService.retryOrder(orderId).subscribe({
      next: () => {
        this.loadOrders();
      },
      error: (error) => {
        console.error('Error retrying order:', error);
      }
    });
  };

  loadOrders(): void {
    this.orderService.getOrders().subscribe({
      next: (orders) => {
        this.orders.set(orders);
      },
      error: (error) => {
        console.error('Error cargando pedidos:', error);
      }
    });
  }

}
