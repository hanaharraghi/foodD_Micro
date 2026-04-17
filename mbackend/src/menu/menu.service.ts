import { Injectable } from '@nestjs/common';
import axios from 'axios';

@Injectable()
export class MenuService {
  // Use 8083 as your default gateway since that is where your API is
  private readonly gatewayBaseUrl =
    process.env.JAVA_GATEWAY_URL ?? 'http://localhost:8083';

  async getAllMenus(authorization?: string) {
    // UPDATED: Changed from /api/menus/all to /api/restaurants
    const response = await axios.get(`${this.gatewayBaseUrl}/api/restaurants`, {
      headers: authorization ? { Authorization: authorization } : undefined,
    });

    return response.data; // This returns the list of restaurants with their menus
  }
}