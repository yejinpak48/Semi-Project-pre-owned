<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f4e3fc2c9daf2b7db45d9123aa83e290"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=clusterer"></script>
<style>
</style>
<body>
	<div id="map" style="width: 500px; height: 400px;"></div>
	<script>
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(37.499117, 127.032882), //지도의 중심좌표.
			level : 3
		//지도의 레벨(확대, 축소 정도)
		};

		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

		var markerPosition = new kakao.maps.LatLng(37.499117, 127.032882);
		var marker = new kakao.maps.Marker({
			position : markerPosition
		});
		marker.setMap(map);
	</script>
</body>
</html>