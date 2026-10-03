import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Order } from '../../models/order';
import { OrderService } from '../../services/order.service';

@Component({
  imports: [FormsModule],
  selector: 'app-order-form',
  styleUrl: './order-form.css',
  templateUrl: './order-form.html',
})

export class OrderForm {

  @Output() orderCreated = new EventEmitter<Order>();


  order : Order = {
    customer: '',
    product: '',
    quantity: 1,
    price: 0,
  };

  constructor(private orderService: OrderService) {}

  submit(): void {
    this.orderService.createOrder(this.order).subscribe({
      next: (createdOrder) => {
        console.log('Order created:', createdOrder);
        this.orderCreated.emit(createdOrder);
        this.order = {
          customer: '',
          product: '',
          quantity: 1,
          price: 0,
        };       
      },
      error: (error) => {
        console.error('Error creating order:', error);
      }
    });
  }
}
