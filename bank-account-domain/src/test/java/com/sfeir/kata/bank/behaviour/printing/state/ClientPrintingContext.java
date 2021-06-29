package com.sfeir.kata.bank.behaviour.printing.state;

import java.io.PrintStream;

import com.sfeir.kata.bank.behaviour.state.ClientContext;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientPrintingContext extends ClientContext {

		private PrintStream printer;
}
