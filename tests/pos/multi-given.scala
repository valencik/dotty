trait A
trait B
trait C

def fancy with (a: A, b: B, c: C) = "Fancy!"
def foo(implicit a: A, b: B, c: C) = "foo"

given A, B {}

given ops: A with B {}