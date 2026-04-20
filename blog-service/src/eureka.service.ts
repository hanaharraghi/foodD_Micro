import { Injectable, OnModuleDestroy, OnModuleInit } from '@nestjs/common';
// eslint-disable-next-line @typescript-eslint/no-var-requires
import { Eureka } from 'eureka-js-client';

@Injectable()
export class EurekaService implements OnModuleInit, OnModuleDestroy {
  private client: any;

  onModuleInit() {
    this.client = new Eureka({
      instance: {
        app: 'BLOG-SERVICE',
        instanceId: 'blog-service:3001',
        hostName: 'blog-service',
        ipAddr: 'blog-service',
        statusPageUrl: 'http://blog-service:3001',
        port: {
          '$': 3001,
          '@enabled': true,
        },
        vipAddress: 'BLOG-SERVICE',
        dataCenterInfo: {
          '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
          name: 'MyOwn',
        },
      },
      eureka: {
        host: 'eureka-server',
        port: 8761,
        servicePath: '/eureka/apps/',
      },
    });

    this.client.start((error: unknown) => {
      if (error) {
        console.error('Eureka registration failed:', error);
      } else {
        console.log('BLOG-SERVICE registered in Eureka');
      }
    });
  }

  onModuleDestroy() {
    if (this.client) {
      this.client.stop(() => {
        console.log('BLOG-SERVICE unregistered from Eureka');
      });
    }
  }
}