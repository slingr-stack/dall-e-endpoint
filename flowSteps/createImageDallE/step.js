step.createImageDallE = function (inputs) {

	var inputsLogic = {
		prompt: inputs.prompt || "",
		url: inputs.url || true,
		size: inputs.size || "256x256",
		n: inputs.n || 1,
		user: inputs.user || ""
	};

	var options = {
		path: "/v1/images/generations",
		body: inputsLogic
	}

	return endpoint._post(options);
};