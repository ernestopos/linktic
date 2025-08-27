package com.linktic.api.infrastructure.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseWrapper {
	@JsonProperty("errors")
	private List<ErrorResponse> errors;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ErrorResponse {

		@Builder.Default
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
		private LocalDateTime timestamp = LocalDateTime.now();

		private String path;

		@JsonProperty("error_code")
		private String errorCode;

		@JsonProperty("error_message")
		private String errorMessage;

		private String details;
	}
}
