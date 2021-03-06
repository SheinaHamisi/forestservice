import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class DatabaseRule extends ExternalResource {
    @Override
    public void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "0987654321");
    }

    @Override
    public void after() {
        String deleteAnimalsQuery = "DELETE FROM animals *";
        String deleteSightingsQuery = "DELETE FROM sightings *";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(deleteAnimalsQuery)
                    .executeUpdate();
            con.createQuery(deleteSightingsQuery)
                    .executeUpdate();
        }
    }
}
