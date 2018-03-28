package jptrade.core.main;

public class Main {

	public static void main(String[] args) {
		System.out.println ("JP Trade");
		MainLoop mainloop = new MainLoop ();
		mainloop.process(args[0]);
	}

}
