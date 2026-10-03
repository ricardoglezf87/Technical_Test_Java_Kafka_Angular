import { Component, OnInit, signal } from '@angular/core';
import { Order } from './models/order';
import { OrderService } from './services/order.service';
import { CommonModule } from '@angular/common';

@Component({
  imports: [CommonModule],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App implements OnInit {
  
  orders: Order[] = [];

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
   
    this.orderService.getOrders().subscribe({
      next:(data) => {
      console.log(data);
      this.orders = data;
    },
    error:(error) => {
      console.error('Error fetching orders:', error);
    }});
  }
}
