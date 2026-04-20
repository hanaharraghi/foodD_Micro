import { Controller, Get, HttpCode } from '@nestjs/common';

@Controller()
export class AppController {
  @Get()
  getRoot() {
    return {
      message: 'Blog service is running',
      endpoints: ['/blogs'],
    };
  }

  @Get('favicon.ico')
  @HttpCode(204)
  getFavicon(): void {}
}
