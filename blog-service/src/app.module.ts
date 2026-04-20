import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { BlogModule } from './blog/blog.module';
import { AppController } from './app.controller';
import { EurekaService } from './eureka.service';

@Module({
  controllers: [AppController],
  imports: [
    MongooseModule.forRoot(process.env.MONGO_URI || 'mongodb://mongo:27017/blog_db'),
    BlogModule,
  ],
  providers: [EurekaService],
})
export class AppModule {}