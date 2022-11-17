package com.sfeir.kata.bank.domain.behaviour.account.operation.date.factory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;

import io.vavr.Function0;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationDateFactory {

		public static Function0<OperationDate> now() {

				return OperationTimeInitializer.initTime()
				                               .andThen(OperationDate::new);
		}

		private static class OperationTimeInitializer {

				private static Function0<TemporalAccessor>
				    initTime() {
						return () -> Instant.now()
						                    .atZone(ZoneId.systemDefault());
				}
		}
}
