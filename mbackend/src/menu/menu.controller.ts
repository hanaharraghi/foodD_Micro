import { Controller, Get, Headers } from '@nestjs/common';
import { MenuService } from './menu.service';

@Controller('menu')
export class MenuController {
  constructor(private readonly menuService: MenuService) {}

  @Get()
  getMenu(@Headers('authorization') authorization?: string) {
    return this.menuService.getAllMenus(authorization);
  }
}
