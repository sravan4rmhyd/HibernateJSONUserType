package org.examples;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

public class OracleIntervalDayToSecondType extends AbstractUserType<Duration> {
	private static final long serialVersionUID = 1L;
	public static final OracleIntervalDayToSecondType INSTANCE = new OracleIntervalDayToSecondType();

	public OracleIntervalDayToSecondType() {
		super(Duration.class);
	}

	private static final int SQL_COLUMN_TYPE = -104;
	private static final String INTERVAL_TOKENS = "%1$2d %2$2d:%3$2d:%4$2d.0";
	private static final Pattern INTERVAL_PATTERN = Pattern.compile("(\\d+)\\s(\\d+):(\\d+):(\\d+)\\.\\d+");

	@Override
	public int[] sqlTypes() {
		return new int[] { SQL_COLUMN_TYPE };
	}

	@Override
	protected void set(PreparedStatement st, Duration value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
			st.setNull(index, SQL_COLUMN_TYPE);
		} else {
			final int days = (int) value.toDays();
			final int hours = (int) (value.toHours() % 24);
			final int minutes = (int) (value.toMinutes() % 60);
			final int seconds = (int) (value.getSeconds() % 60);

			st.setString(index, String.format(INTERVAL_TOKENS, days, hours, minutes, seconds));
		}
	}

	@Override
	protected Duration get(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		final String intervalValue = rs.getString(names[0]);

		if (intervalValue == null) {
			return null;
		}

		Matcher matcher = INTERVAL_PATTERN.matcher(intervalValue);

		if (matcher.matches()) {
			Integer days = Integer.parseInt(matcher.group(1));
			Integer hours = Integer.parseInt(matcher.group(2));
			Integer minutes = Integer.parseInt(matcher.group(3));
			Integer seconds = Integer.parseInt(matcher.group(4));

			return Duration.ofDays(days).plus(hours, ChronoUnit.HOURS).plus(minutes, ChronoUnit.MINUTES)
					.plus((long) Math.floor(seconds), ChronoUnit.SECONDS);
		}

		throw new IllegalArgumentException(
				"The parsed interval " + intervalValue + " does not match the expected pattern: " + INTERVAL_PATTERN);
	}
}
