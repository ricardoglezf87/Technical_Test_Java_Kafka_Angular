import { TestBed } from '@angular/core/testing';
import {
  HttpTestingController,
  provideHttpClientTesting
} from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { OrderService } from './order.service';
import { Order } from '../models/order';

describe('OrderService', () => {

  let service: OrderService;
  let httpMock: HttpTestingController;

  const apiUrl = 'http://localhost:8080/api/orders';

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        OrderService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });

    service = TestBed.inject(OrderService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get orders', () => {

    const mockOrders: Order[] = [
      {
        id: '1',
        customer: 'Ricardo',
        product: 'Laptop',
        quantity: 1,
        price: 1299.99,
        status: 'COMPLETED'
      },
      {
        id: '2',
        customer: 'Ana',
        product: 'Monitor',
        quantity: 2,
        price: 249.99,
        status: 'CREATED'
      }
    ];

    service.getOrders().subscribe((orders) => {
      expect(orders.length).toBe(2);
      expect(orders).toEqual(mockOrders);
    });

    const req = httpMock.expectOne(apiUrl);

    expect(req.request.method).toBe('GET');

    req.flush(mockOrders);
  });

  it('should create an order', () => {

    const newOrder: Order = {
      customer: 'Ricardo',
      product: 'Keyboard',
      quantity: 1,
      price: 99.99
    };

    const createdOrder: Order = {
      id: '123',
      customer: 'Ricardo',
      product: 'Keyboard',
      quantity: 1,
      price: 99.99,
      status: 'CREATED'
    };

    service.createOrder(newOrder).subscribe((order) => {
      expect(order).toEqual(createdOrder);
      expect(order.id).toBe('123');
      expect(order.status).toBe('CREATED');
    });

    const req = httpMock.expectOne(apiUrl);

    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newOrder);

    req.flush(createdOrder);
  });

});