import { Component, OnInit, NgZone, signal } from '@angular/core';
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

  constructor(
    private orderService: OrderService,
    private ngZone: NgZone
  ) {}

  ngOnInit(): void {   
    this.loadOrders();    
    this.listenOrderEvents();
  }

  listenOrderEvents(): void {
    const eventSource = new EventSource('http://localhost:8080/api/orders/events');    

    eventSource.addEventListener(
      'order-status',
      (event: MessageEvent) => {

        const updatedOrder: Order = JSON.parse(event.data);

        this.ngZone.run(() => {

          this.orders.update(orders =>
            orders.map(order =>
              order.id === updatedOrder.id
                ? updatedOrder
                : order
            )
          );

        });
      }
    );

    eventSource.onerror = error =>{
      console.error(
        'Error SSE. readyState:',
        eventSource.readyState,
        error
      );
    }
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
