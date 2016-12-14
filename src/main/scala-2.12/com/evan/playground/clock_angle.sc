case class Time(h: Int, m: Int, s: Int)

def secSince12(t: Time): Int = 60*60*(t.h%12) + 60*t.m + t.s

def angle(t: Time): Double =
{
	val secs = secSince12(t)
	val t_h_a: Double = secs * (1/120d)
	val t_m_a: Double = secs * (1/2d) % 360d
	val diff = math.abs(t_h_a - t_m_a)
	math.min(360d-diff, diff)
}

angle(Time(12, 10, 0))

secSince12(Time(12, 10, 0))

val a = 600/120d

val b = 600/2d

angle(Time(6, 51, 0))