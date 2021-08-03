
# RecyclerView
* Android에서 데이터들(data list)을 표현하는 대표적인 View
* 현재 화면에 보이는 view는 1page 짜리 view인데

불러오고 보여주는 코드가 내부에서 작동되는 구조이다

## RecyclerView.Adapter 상속 클래스 생성
* 표현하고자 하는 Data와 화면에 보여줄 View를 연결하는 Helper 도구

## RecyclerView.ViewHolder 상속 클래스 생성
* 데이터들을 표현할때 각각의 Item을 화면에 그리는 도구

## item view layout 파일 생성
* RecyclerView에서 필요한 화면 구성요소를 만들 Layout 파일
* 표현하고자 하는 데이터가 10개이다 라고 하면 각각의 item 들은 공통된
* 이때 공통된 각 Item을 그려낼 layout