package rui.crater.beatles;

import java.sql.SQLException;
import java.util.Map;

import rui.crater.tool.DBHelper;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MyPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		DBHelper dbhelper = new DBHelper();

		System.out.println("get page: " + resultItems.getRequest().getUrl());

		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			// System.out.println(entry.getKey() + ":\t" + entry.getValue());

			try {
				java.sql.PreparedStatement preparestatement = dbhelper.prepareStatement("INSERT INTO myhash VALUES(NULL,?,?,CURRENT_TIMESTAMP)");
				preparestatement.setString(1, entry.getKey());
				preparestatement.setString(2, entry.getValue().toString());
				preparestatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		dbhelper.close();
	}

}
