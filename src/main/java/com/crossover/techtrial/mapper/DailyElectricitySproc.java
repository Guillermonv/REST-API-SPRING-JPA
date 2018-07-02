package com.crossover.techtrial.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.crossover.techtrial.dto.DailyElectricity;

@Component
public class DailyElectricitySproc extends StoredProcedure {
    public static final String ATTRIBUTE = "DailyElectricity";

    public DailyElectricitySproc(DataSource ds) {

        super(ds, "AllDailyFromYesterdaySproc");

        declareParameter(new SqlReturnResultSet(ATTRIBUTE, new DailyRowMapper()));
        declareParameter(new SqlParameter("id", Types.VARCHAR));
    }

    public static class DailyRowMapper implements RowMapper<DailyElectricity> {

        @Override
        public DailyElectricity mapRow(ResultSet rs, int i) throws SQLException {
            DailyElectricity domain = new DailyElectricity();
            domain.setDate(rs.getDate("reading_at"));
            domain.setSum(rs.getLong("Dailysum"));
            domain.setMax(rs.getLong("Dailymax"));
            domain.setMin(rs.getLong("Dailymin"));
            domain.setAverage(rs.getDouble("DailyAvg"));
            return domain;
        }

    }
}

