import { Component, EventEmitter, Output } from '@angular/core';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '../../models/order';
import { OrderService } from '../../services/order.service';

@Component({
  imports: [ReactiveFormsModule],
  selector: 'app-order-form',
  styleUrl: './order-form.css',
  templateUrl: './order-form.html',
})

export class OrderForm {

  @Output() orderCreated = new EventEmitter<Order>();

  orderForm = new FormGroup({
    customer: new FormControl('', [Validators.required, Validators.minLength(2)]),
    product: new FormControl('', [Validators.required, Validators.minLength(2)]),
    quantity: new FormControl(1, [Validators.required, Validators.min(1)]),
    price: new FormControl(0, [Validators.required, Validators.min(0.01)]),
  })

  constructor(private orderService: OrderService) {}

  submit(): void {

    if (this.orderForm.invalid) {
      this.orderForm.markAllAsTouched();
      return;
    }

    const order: Order = this.orderForm.getRawValue() as Order;

    this.orderService.createOrder(order).subscribe({
      next: (createdOrder) => {
        console.log('Order created:', createdOrder);
        this.orderCreated.emit(createdOrder);

        this.orderForm.reset({
          customer: '',
          product: '',
          quantity: 1,
          price: 0
        });

      },
      error: (error) => {
        console.error('Error creating order:', error);
      }
    });
  }
}
