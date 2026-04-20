import { IsOptional, IsString } from 'class-validator';

export class UpdateBlogDto {
	@IsString()
	@IsOptional()
	title?: string;

	@IsString()
	@IsOptional()
	content?: string;

	@IsString()
	@IsOptional()
	author?: string;

	@IsString()
	@IsOptional()
	status?: string;
}
