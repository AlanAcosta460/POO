class OuterException extends Exception {
	OuterException(String s) {
		super(s);
	}
	
	OuterException(String s, boolean sth) {
		InnerException2 local = new InnerException2(s);
	}

	static class InnerException extends Exception {
		InnerException(String s) {
			super(s);
		}
	}
	
	class InnerException2 extends Exception {
		
		InnerException2(String s) {
			System.out.println(s);
		}
	}
}
