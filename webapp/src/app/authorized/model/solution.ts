export interface Solution {
	correct: boolean | null,
	stackTrace?: string
}

export interface SolutionUpdate extends Solution {
	taskId: number
}