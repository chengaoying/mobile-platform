package cn.ohyeah.mobile.platform.view;

import java.util.Map;

public class MappingJacksonJsonView extends org.springframework.web.servlet.view.json.MappingJacksonJsonView {

	protected Object filterModel(Map<String, Object> model) {
		Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
		if (result.size() == 1) {
			return result.values().iterator().next();
		} else {
			return result;
		}
	}

}
