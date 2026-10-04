import { CommonModule } from '@angular/common';
import { Component, input, output } from '@angular/core';
import { Order } from '../../models/order';
import id from '@angular/common/locales/extra/id';

@Component({
  imports: [CommonModule],
  selector: 'app-order-list',
  styleUrl: './order-list.css',
  templateUrl: './order-list.html',
})
export class OrderList {
  
  orders = input<Order[]>([]);

  retry = output<string>();

  retryOrder(orderId: string): void {
    if(id){
      this.retry.emit(orderId);
    }
  }

}
